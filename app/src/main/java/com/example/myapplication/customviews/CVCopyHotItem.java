package com.example.myapplication.customviews;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.R;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.res.ResourcesCompat;

public class CVCopyHotItem extends FrameLayout {

    private Context mContext;
    private TextView tvContent;
    private Button btnCopy;
    private EditText loginEdittext;

    public CVCopyHotItem(@NonNull Context context) {
        super(context);
        mContext = context;
    }

    public CVCopyHotItem(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        init(mContext, attrs);
    }

    public CVCopyHotItem(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        init(mContext, attrs);
    }

    public CVCopyHotItem(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        mContext = context;
        init(mContext, attrs);
    }


    private void init(Context mContext, AttributeSet attrs) {

        View view = LayoutInflater.from(mContext).inflate(R.layout.customview_copy_item, this);

        tvContent = view.findViewById(R.id.tvContent);
        btnCopy = view.findViewById(R.id.btnCopy);

        TypedArray values = mContext.obtainStyledAttributes(attrs, R.styleable.CVCopyHotItem, 0, 0);

        String contentText = values.getString(R.styleable.CVCopyHotItem_cv_hot_copy_item_ContentText);
        int textColor = values.getInt(R.styleable.CVCopyHotItem_cv_hot_color, -1);

        if (contentText != null)
            tvContent.setText(contentText);

        if (textColor != -1) {
            handleTextColor(textColor);
        }


        if (!isInEditMode())
            tvContent.setTypeface(ResourcesCompat.getFont(mContext, R.font.regular));


        btnCopy.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                copy(tvContent.getText().toString());
            }
        });


        values.recycle();
    }

    private void handleTextColor(int textColor) {
        switch (textColor) {
            case 1:
                tvContent.setTextColor(mContext.getResources().getColor(R.color.blue));
                break;

            case 2:
                tvContent.setTextColor(mContext.getResources().getColor(R.color.yellow));
                break;

            case 3:
                tvContent.setTextColor(mContext.getResources().getColor(R.color.red));
                break;
        }
    }


    private void copy(String copyText) {
        android.content.ClipboardManager clipboard = (android.content.ClipboardManager) mContext.getSystemService(Context.CLIPBOARD_SERVICE);
        android.content.ClipData clip = android.content.ClipData.newPlainText("DEPOSIT_INFO", copyText);
        clipboard.setPrimaryClip(clip);

        Toast.makeText(mContext, "Kopyalandı " + copyText, Toast.LENGTH_SHORT).show();
    }


    public void setText(String text) {
        tvContent.setText(text);
    }

}
