package com.example.myapplication.customviews;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.R;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.res.ResourcesCompat;

public class CVCopyItem extends FrameLayout {

    private Context mContext;
    private TextView tvContent;
    private Button btnCopy;
    private ConstraintLayout root;

    public CVCopyItem(@NonNull Context context) {
        super(context);
        mContext = context;
    }

    public CVCopyItem(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        init(mContext, attrs);
    }


    public CVCopyItem(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        init(mContext, attrs);
    }


    private void init(Context mContext, AttributeSet attrs) {

        View view = LayoutInflater.from(mContext).inflate(R.layout.customview_copy_item, this);

        TypedArray values = mContext.obtainStyledAttributes(attrs, R.styleable.CVCopyItem, 0, 0);

        int bgcolor = values.getColor(R.styleable.CVCopyItem_cv_copy_bgColor, -1);
        int btnBgColor = values.getInt(R.styleable.CVCopyItem_cv_copy_btnBgColor, -1);
        String contentText = values.getString(R.styleable.CVCopyItem_cv_copy_ContentText);

        tvContent = view.findViewById(R.id.tvContent);
        btnCopy = view.findViewById(R.id.btnCopy);
        root = view.findViewById(R.id.root);

        setListeners();

        if (bgcolor != -1)
            root.setBackgroundColor(bgcolor);

        if (btnBgColor != -1)
            handleBtnBackground(btnBgColor);

        if (contentText != null)
            tvContent.setText(contentText);


        if (!isInEditMode())
            tvContent.setTypeface(ResourcesCompat.getFont(mContext, R.font.medium));


        values.recycle();


    }

    private void handleBtnBackground(int btnBgColor) {
        switch (btnBgColor) {
            case 1:
                btnCopy.setBackgroundColor(mContext.getResources().getColor(R.color.yellow));
                break;

            case 2:
                btnCopy.setBackgroundColor(mContext.getResources().getColor(R.color.red));
                break;
        }
    }

    private void setListeners() {
        btnCopy.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = tvContent.getText().toString();
                if (!text.isEmpty())
                    copy(text);
            }
        });
    }

    private void copy(String copyText) {
        android.content.ClipboardManager clipboard = (android.content.ClipboardManager) mContext.getSystemService(Context.CLIPBOARD_SERVICE);
        android.content.ClipData clip = android.content.ClipData.newPlainText("DEPOSIT_INFO", copyText);
        clipboard.setPrimaryClip(clip);

        Toast.makeText(mContext, "Kopyalandı", Toast.LENGTH_SHORT).show();
    }

    public void setContentText(String text) {
        tvContent.setText(text);
    }
}
