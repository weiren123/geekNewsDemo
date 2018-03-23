package com.example.administrator.geeknewsdemo.ui.zhihu.fragment;

import android.os.Handler;
import android.os.Message;
import android.view.MotionEvent;
import android.view.SurfaceView;
import android.view.View;
import android.view.WindowManager;
import android.widget.CheckBox;
import android.widget.CheckedTextView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.administrator.geeknewsdemo.R;
import com.example.administrator.geeknewsdemo.base.SimpleFragment;
import com.example.administrator.geeknewsdemo.utils.ConvertToUtils;
import com.example.administrator.geeknewsdemo.utils.ToastUtil;
import com.example.administrator.geeknewsdemo.widget.ProgressView;
import com.orhanobut.logger.Logger;
import com.yixia.camera.MediaRecorderBase;
import com.yixia.camera.MediaRecorderNative;
import com.yixia.camera.MediaRecorderSystem;
import com.yixia.camera.VCamera;
import com.yixia.camera.model.MediaObject;
import com.yixia.camera.util.DeviceUtils;
import com.yixia.camera.util.FileUtils;
import com.yixia.videoeditor.adapter.UtilityAdapter;

import java.io.File;

import butterknife.BindView;

/**
 * Created by Administrator on 2018/3/16.
 */

public class GankFragment extends SimpleFragment implements MediaRecorderBase.OnErrorListener, MediaRecorderBase.OnEncodeListener, View.OnClickListener {
    @BindView(R.id.record_camera_led)
    CheckBox recordCameraLed;
    @BindView(R.id.record_camera_switcher)
    CheckBox recordCameraSwitcher;
    @BindView(R.id.title_next)
    ImageView titleNext;
    @BindView(R.id.record_progress)
    ProgressView recordProgress;
    @BindView(R.id.record_preview)
    SurfaceView recordPreview;
    @BindView(R.id.record_focusing)
    ImageView recordFocusing;
    @BindView(R.id.record_delete)
    CheckedTextView recordDelete;
    @BindView(R.id.record_controller)
    ImageView recordController;
    @BindView(R.id.bottom_layout)
    RelativeLayout bottomLayout;

    /** SDK视频录制对象 */
    private MediaRecorderBase mMediaRecorder;
    /** 视频信息 */
    private MediaObject mMediaObject;
    /** 需要重新编译（拍摄新的或者回删） */
    private boolean mRebuild;
    /** 录制最长时间 */
    public final static int RECORD_TIME_MAX = 100 * 1000;
    /** 录制最小时间 */
    public final static int RECORD_TIME_MIN = 10 * 1000;
    /** 刷新进度条 */
    private static final int HANDLE_INVALIDATE_PROGRESS = 0;
    /** 延迟拍摄停止 */
    private static final int HANDLE_STOP_RECORD = 1;
    /** 对焦 */
    private static final int HANDLE_HIDE_RECORD_FOCUS = 2;

    /** 是否是点击状态 */
    private volatile boolean mPressedStatus;

    /** 是否已经释放 */
    private volatile boolean mReleased;
    /** on */
    private boolean mCreated;
    private int mWindowWidth;
    private int mFocusWidth;
    private int mBackgroundColorNormal;
    private int mBackgroundColorPress;

    @Override
    public void onResume() {
        super.onResume();
        UtilityAdapter.freeFilterParser();
        UtilityAdapter.initFilterParser();

        if (mMediaRecorder == null) {
            initMediaRecorder();
        } else {
            recordCameraLed.setChecked(false);
            mMediaRecorder.prepare();
            recordProgress.setData(mMediaObject);
        }
    }
    @Override
    public void onPause() {
        super.onPause();
        stopRecord();
        UtilityAdapter.freeFilterParser();
        if (!mReleased) {
            if (mMediaRecorder != null)
                mMediaRecorder.release();
        }
        mReleased = false;
    }
    private void initMediaRecorder() {

        mMediaRecorder = new MediaRecorderNative();
        mRebuild = true;
        titleNext.setOnClickListener(this);
        mMediaRecorder.setOnErrorListener(this);
        mMediaRecorder.setOnEncodeListener(this);
        File f = new File(VCamera.getVideoCachePath());
        if (!FileUtils.checkFile(f)) {
            f.mkdirs();
        }
        String key = String.valueOf(System.currentTimeMillis());
        mMediaObject = mMediaRecorder.setOutputDirectory(key,
                VCamera.getVideoCachePath() + key);
        mMediaRecorder.setSurfaceHolder(recordPreview.getHolder());
        mMediaRecorder.prepare();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_media_recorder;
    }

    @Override
    protected void initEventData() {
        mCreated = false;
        mActivity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON); // 防止锁屏
        loadIntent();
        if(DeviceUtils.hasICS()){
            recordPreview.setOnTouchListener(mSurfaceViewOnTouchListener);
            bottomLayout.setOnTouchListener(mOnVideoControllerTouchListener);
            // 是否支持前置摄像头
            if (MediaRecorderBase.isSupportFrontCamera()) {
                recordCameraSwitcher.setOnClickListener(this);
            } else {
                recordCameraSwitcher.setVisibility(View.GONE);
            }
            // 是否支持闪光灯
            if (DeviceUtils.isSupportCameraLedFlash(mActivity.getPackageManager())) {
                recordCameraLed.setOnClickListener(this);
            } else {
                recordCameraLed.setVisibility(View.GONE);
            }

            try {
                recordFocusing.setImageResource(R.mipmap.video_focus);
            } catch (OutOfMemoryError e) {
                Logger.e(e.getMessage());
            }

            recordProgress.setMaxDuration(RECORD_TIME_MAX);
            initSurfaceView();

        }
        mCreated = true;
    }

    private void initSurfaceView() {
        final int w = DeviceUtils.getScreenWidth(mContext);
        ((RelativeLayout.LayoutParams) bottomLayout.getLayoutParams()).topMargin = w;
        int width = w;
        int height = w * 4 / 3;
        //
        RelativeLayout.LayoutParams lp = (RelativeLayout.LayoutParams) recordPreview
                .getLayoutParams();
        lp.width = width;
        lp.height = height;
        recordPreview.setLayoutParams(lp);
    }

    private void loadIntent() {
        mWindowWidth = DeviceUtils.getScreenWidth(mContext);

        mFocusWidth = ConvertToUtils.dipToPX(mContext, 64);
        mBackgroundColorNormal = getResources().getColor(R.color.black);// camera_bottom_bg
        mBackgroundColorPress = getResources().getColor(
                R.color.camera_bottom_press_bg);
    }

    View.OnTouchListener mSurfaceViewOnTouchListener = new View.OnTouchListener(){

        @Override
        public boolean onTouch(View v, MotionEvent event) {
            return false;
        }
    };
    View.OnTouchListener mOnVideoControllerTouchListener = new View.OnTouchListener(){

        @Override
        public boolean onTouch(View v, MotionEvent event) {
            if (mMediaRecorder == null) {
                return false;
            }

            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    // 检测是否手动对焦
                    // 判断是否已经超时
                    if (mMediaObject.getDuration() >= RECORD_TIME_MAX) {
                        return true;
                    }

                    // 取消回删
                    if (cancelDelete())
                        return true;

                    startRecord();

                    break;

                case MotionEvent.ACTION_UP:
                    // 暂停
                    if (mPressedStatus) {
                        stopRecord();

                        // 检测是否已经完成
                        if (mMediaObject.getDuration() >= RECORD_TIME_MAX) {
                            titleNext.performClick();
                        }
                    }
                    break;
            }
            return true;
        }
    };

    private void stopRecord() {
        mPressedStatus = false;
        recordController.setImageResource(R.mipmap.record_controller_normal);
        bottomLayout.setBackgroundColor(mBackgroundColorNormal);

        if (mMediaRecorder != null) {
            mMediaRecorder.stopRecord();
        }

        recordDelete.setVisibility(View.VISIBLE);
        recordCameraSwitcher.setEnabled(true);
        recordCameraLed.setEnabled(true);

        mHandler.removeMessages(HANDLE_STOP_RECORD);
        checkStatus();
    }

    private int checkStatus() {
        int duration = 0;
        if (!mActivity.isFinishing() && mMediaObject != null) {
            duration = mMediaObject.getDuration();
            if (duration < RECORD_TIME_MIN) {
                if (duration == 0) {
                    recordCameraSwitcher.setVisibility(View.VISIBLE);
                    recordDelete.setVisibility(View.GONE);
                }
                // 视频必须大于3秒
                if (titleNext.getVisibility() != View.INVISIBLE)
                    titleNext.setVisibility(View.INVISIBLE);
            } else {
                // 下一步
                if (titleNext.getVisibility() != View.VISIBLE) {
                    titleNext.setVisibility(View.VISIBLE);
                }
            }
        }
        return duration;
    }

    private void startRecord() {
        if (mMediaRecorder != null) {
            MediaObject.MediaPart part = mMediaRecorder.startRecord();
            if (part == null) {
                return;
            }

            // 如果使用MediaRecorderSystem，不能在中途切换前后摄像头，否则有问题
            if (mMediaRecorder instanceof MediaRecorderSystem) {
                recordCameraSwitcher.setVisibility(View.GONE);
            }
            recordProgress.setData(mMediaObject);
        }

        mRebuild = true;
        mPressedStatus = true;
        recordController.setImageResource(R.mipmap.record_controller_press);
        bottomLayout.setBackgroundColor(mBackgroundColorPress);

        if (mHandler != null) {
            mHandler.removeMessages(HANDLE_INVALIDATE_PROGRESS);
            mHandler.sendEmptyMessage(HANDLE_INVALIDATE_PROGRESS);

            mHandler.removeMessages(HANDLE_STOP_RECORD);
            mHandler.sendEmptyMessageDelayed(HANDLE_STOP_RECORD,
                    RECORD_TIME_MAX - mMediaObject.getDuration());
        }
        recordDelete.setVisibility(View.GONE);
        recordCameraSwitcher.setEnabled(false);
        recordCameraLed.setEnabled(false);
    }
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case HANDLE_INVALIDATE_PROGRESS:
                    if (mMediaRecorder != null && !mActivity.isFinishing()) {
                        if (recordProgress != null)
                            recordProgress.invalidate();
                        if (mPressedStatus)
                            sendEmptyMessageDelayed(0, 30);
                    }
                    break;
            }
        }
    };

    /** 取消回删 */
    private boolean cancelDelete() {
        if (mMediaObject != null) {
            MediaObject.MediaPart part = mMediaObject.getCurrentPart();
            if (part != null && part.remove) {
                part.remove = false;
                recordDelete.setChecked(false);

                if (recordProgress != null)
                    recordProgress.invalidate();

                return true;
            }
        }
        return false;
    }
    @Override
    public void onVideoError(int what, int extra) {

    }

    @Override
    public void onAudioError(int what, String message) {

    }

    @Override
    public void onEncodeStart() {
        showProgress("", getString(R.string.record_camera_progress_message));
    }

    @Override
    public void onEncodeProgress(int progress) {
        Logger.e("[MediaRecorderActivity]onEncodeProgress..." + progress);
    }

    @Override
    public void onEncodeComplete() {
        hideProgress();
        ToastUtil.shortShow("转码完成");
//        Intent intent = new Intent(this, MediaPreviewActivity.class);
//        Bundle bundle = getIntent().getExtras();
//        if (bundle == null)
//            bundle = new Bundle();
//        bundle.putSerializable(CommonIntentExtra.EXTRA_MEDIA_OBJECT,
//                mMediaObject);
//        bundle.putString("output", mMediaObject.getOutputTempVideoPath());
//        bundle.putBoolean("Rebuild", mRebuild);
//        intent.putExtras(bundle);
//        startActivity(intent);
        mRebuild = false;
    }

    /**
     * 转码失败 检查sdcard是否可用，检查分块是否存在
     */
    @Override
    public void onEncodeError() {
        hideProgress();
        Toast.makeText(mContext, R.string.record_video_transcoding_faild,
                Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View v) {
        if (mHandler.hasMessages(HANDLE_STOP_RECORD)) {
            mHandler.removeMessages(HANDLE_STOP_RECORD);
        }
        switch (v.getId()){
            case R.id.title_next:
                mMediaRecorder.startEncoding();
//                mActivity.finish();
                break;
        }
    }
}
