package com.lanyoumobility.mobility_webview.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.method.DigitsKeyListener;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.CheckedTextView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import com.lanyoumobility.mobility_webview.R;


public class PwdVisibleLayout extends LinearLayout implements OnClickListener {
	private EditText et_password;
	private CheckedTextView ctv;
	private ImageView img_clear;

	private float textSize;
	private int textColor, hintColor;
	private Drawable checkMark;
	private String hint, text,digits;
//	private boolean mbDisplayFlg=false;

	public PwdVisibleLayout(Context context, AttributeSet attrs) {
		super(context, attrs);
		TypedArray a = context.obtainStyledAttributes(attrs,
				R.styleable.pwdVisible);
		textSize = a.getDimension(R.styleable.pwdVisible_textSize, -1);
		hintColor = a.getColor(R.styleable.pwdVisible_textColorHint, -1);
		textColor = a.getColor(R.styleable.pwdVisible_textColor, -1);
		hint = a.getString(R.styleable.pwdVisible_hint);
		text = a.getString(R.styleable.pwdVisible_text);
		checkMark = a.getDrawable(R.styleable.pwdVisible_checkMark);
		digits = a.getString(R.styleable.pwdVisible_digits);

		a.recycle();

		initView();
	}

	private void initView() {
		LinearLayout layout = (LinearLayout) LayoutInflater.from(getContext())
				.inflate(R.layout.layout_pwd_visible, this);
		et_password = (EditText) layout.findViewById(R.id.et_password);
		ctv = (CheckedTextView) layout.findViewById(R.id.ctv);
		img_clear = (ImageView) layout.findViewById(R.id.img_clear);
		//默认密码是密码样式并且由于进入页面未获得焦距，小眼睛应隐藏
		hidePassword();
		hideDrawable();
		if (textSize != -1) {
			et_password.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize);
		}
		if (textColor != -1) {
			et_password.setTextColor(textColor);
		}
		if (!TextUtils.isEmpty(hint)) {
			et_password.setHint(hint);
		}
		if(!TextUtils.isEmpty(text)){
			et_password.setText(text);
		}
		if (hintColor != -1) {
			et_password.setHintTextColor(hintColor);
		}
		if(checkMark != null){
			ctv.setCheckMarkDrawable(checkMark);
		}
		if (digits != null){
			et_password.setOnKeyListener((OnKeyListener) DigitsKeyListener.getInstance(digits));
		}
		ctv.setOnClickListener(this);
		img_clear.setOnClickListener(this);

		et_password.setOnFocusChangeListener(new OnFocusChangeListener() {

			@Override
			public void onFocusChange(View v, boolean hasFocus) {
				if(hasFocus){
					if(!TextUtils.isEmpty(getPwd())){
						//如果获取焦距并且密码不为空，则显示小眼睛和清除图片
						showDrawable();
					}
				} else {
					if(ctv.isChecked()){
						ctv.toggle();
						hidePassword();
					}
					hideDrawable();
				}
			}
		});

		et_password.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				// TODO Auto-generated method stub

			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after) {
				// TODO Auto-generated method stub

			}

			@Override
			public void afterTextChanged(Editable s) {
				// TODO Auto-generated method stub
				if (!hasFocus()) {
					return;
				}
				String afterChangeText = s.toString();
				if (!TextUtils.isEmpty(afterChangeText)) {
					showDrawable();
				} else {
					hideDrawable();
				}
			}
		});
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		int i = v.getId();
		if (i == R.id.ctv) {
			ctv.toggle();
			if (!ctv.isChecked()) {
				hidePassword();
			} else {
				showPassword();
			}
			et_password.setSelection(et_password.getText().length());

		} else if (i == R.id.img_clear) {
			et_password.setText("");
			hidePassword();
			hideDrawable();
			if (ctv.isChecked()) {
				ctv.toggle();
			}

		}

		et_password.requestFocus();
	}

	/**
	 * 隐藏密码
	 */
	private void hidePassword(){
		et_password.setTransformationMethod(PasswordTransformationMethod.getInstance());
		et_password.postInvalidate();
//		et_password.setInputType(InputType.TYPE_CLASS_TEXT
//				| InputType.TYPE_TEXT_VARIATION_PASSWORD);
	}

	/**
	 * 显示密码
	 */
	private void showPassword(){
		et_password.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
		et_password.postInvalidate();
//		et_password.setInputType(InputType.TYPE_CLASS_TEXT
//				| InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
	}

	/**
	 * 隐藏小眼睛和清除图片
	 */
	private void hideDrawable(){
		img_clear.setVisibility(View.GONE);
		ctv.setVisibility(View.GONE);
	}

	/**
	 * 显示小眼睛和清除图片
	 */
	private void showDrawable(){
		img_clear.setVisibility(View.VISIBLE);
		ctv.setVisibility(View.VISIBLE);
	}
	public void setText(String pasword) {
		 et_password.setText(pasword);
	}

	public String getPwd() {
		return et_password.getText().toString();
	}

	public EditText getEditText(){
		return et_password;
	}
}
