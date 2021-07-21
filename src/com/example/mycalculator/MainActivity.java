package com.example.mycalculator;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.app.Activity;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mycalculator.Calculator;

public class MainActivity extends Activity implements OnClickListener {
	protected StringBuffer expressionString;
	List<String> historyRecord=new ArrayList<String>();
	private int statusCode=0;
	private int click;
	private long exitTime;

	private Button btn1;
	private Button btn2;
	private Button btn3;
	private Button btn4;
	private Button btn5;
	private Button btn6;
	private Button btn7;
	private Button btn8;
	private Button btn9;
	private Button btn0;

	private Button btnAdd;
	private Button btnSub;
	private Button btnMul;
	private Button btnDiv;
	private Button btnClear;
	private Button btnDel;
	private Button btnDot;
	private Button btnLeftBracket;
	private Button btnRightBracket;
	private Button btnEqual;
	private Button btnRecord;
	private EditText editText;
	
	SoundPool mySoundPool;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		expressionString = new StringBuffer("");
		mySoundPool = new SoundPool(1, AudioManager.STREAM_MUSIC, 0);

		btn0 = (Button) findViewById(R.id.btn_0);
		btn1 = (Button) findViewById(R.id.btn_1);
		btn2 = (Button) findViewById(R.id.btn_2);
		btn3 = (Button) findViewById(R.id.btn_3);
		btn4 = (Button) findViewById(R.id.btn_4);
		btn5 = (Button) findViewById(R.id.btn_5);
		btn6 = (Button) findViewById(R.id.btn_6);
		btn7 = (Button) findViewById(R.id.btn_7);
		btn8 = (Button) findViewById(R.id.btn_8);
		btn9 = (Button) findViewById(R.id.btn_9);

		btnAdd = (Button) findViewById(R.id.btn_add);
		btnSub = (Button) findViewById(R.id.btn_sub);
		btnMul = (Button) findViewById(R.id.btn_mul);
		btnDiv = (Button) findViewById(R.id.btn_div);

		btnClear = (Button) findViewById(R.id.btn_clear);
		btnDel = (Button) findViewById(R.id.btn_del);
		btnDot = (Button) findViewById(R.id.btn_point);
		btnEqual = (Button) findViewById(R.id.btn_equal);
		btnRecord = (Button) findViewById(R.id.btn_record);
		btnLeftBracket = (Button) findViewById(R.id.btn_left_bracket);
		btnRightBracket = (Button) findViewById(R.id.btn_right_bracket);

		editText = (EditText) findViewById(R.id.editText);

		btn0.setOnClickListener(this);
		btn1.setOnClickListener(this);
		btn2.setOnClickListener(this);
		btn3.setOnClickListener(this);
		btn4.setOnClickListener(this);
		btn5.setOnClickListener(this);
		btn6.setOnClickListener(this);
		btn7.setOnClickListener(this);
		btn8.setOnClickListener(this);
		btn9.setOnClickListener(this);
		btnAdd.setOnClickListener(this);
		btnSub.setOnClickListener(this);
		btnMul.setOnClickListener(this);
		btnDiv.setOnClickListener(this);
		btnClear.setOnClickListener(this);
		btnDel.setOnClickListener(this);
		btnDot.setOnClickListener(this);
		btnEqual.setOnClickListener(this);
		btnRecord.setOnClickListener(this);
		btnLeftBracket.setOnClickListener(this);
		btnRightBracket.setOnClickListener(this);
		editText.setOnClickListener(this);
	}
	
	public boolean judgeExceptionString() {
		String pattren = "[0-9]\\(|\\)[0-9]";
		Pattern rPattern = Pattern.compile(pattren);
		Matcher matcher = rPattern.matcher(expressionString);
		
		if(matcher.find()) {
			return false;
		}
		return true;
	}

	@Override
	public void onClick(View v) {
		
		playSound();
		
		if(statusCode == 1 && v.getId() != R.id.btn_clear) {
			Toast.makeText(this, "运算结束，自动清空表达式\n 点击记录查看历史记录", Toast.LENGTH_SHORT).show();
			expressionString.delete(0, expressionString.length());
			statusCode = 0;
		}
		
		else {
			switch (v.getId()) {
			case R.id.btn_0:
				expressionString.append('0');
				break;
			case R.id.btn_1:
				expressionString.append('1');
				break;
			case R.id.btn_2:
				expressionString.append('2');
				break;
			case R.id.btn_3:
				expressionString.append('3');
				break;
			case R.id.btn_4:
				expressionString.append('4');
				break;
			case R.id.btn_5:
				expressionString.append('5');
				break;
			case R.id.btn_6:
				expressionString.append('6');
				break;
			case R.id.btn_7:
				expressionString.append('7');
				break;
			case R.id.btn_8:
				expressionString.append('8');
				break;
			case R.id.btn_9:
				expressionString.append('9');
				break;
			case R.id.btn_add:
				expressionString.append('+');
				break;
			case R.id.btn_mul:
				expressionString.append('*');
				break;
			case R.id.btn_sub:
				expressionString.append('-');
				break;
			case R.id.btn_div:
				expressionString.append('/');
				break;
			case R.id.btn_point:
				expressionString.append('.');
				break;
			case R.id.btn_left_bracket:
				expressionString.append('(');
				break;
			case R.id.btn_right_bracket:
				expressionString.append(')');
				break;
			case R.id.btn_record:
				if(historyRecord.size() == 0)
					Toast.makeText(this, "查询不到历史记录", Toast.LENGTH_SHORT).show();
				else {
					StringBuffer recordString = new StringBuffer("正在展示历史记录：\n");
					for(String history:historyRecord)
						recordString.append(history+"\n");
					editText.setText(recordString);
				}
				break;
			case R.id.btn_clear:
				if (statusCode == 1)
					statusCode = 0;
				expressionString.delete(0, expressionString.length());
				break;
			case R.id.btn_del:
				if(expressionString.length()-1>=0)
					expressionString.delete(expressionString.length()-1, expressionString.length());
				break;
			case R.id.btn_equal:
				try {
					if (judgeExceptionString()) {
						Calculator calculator=new Calculator();
						double ans = calculator.calculate(expressionString.toString());
						expressionString.append('=');
						expressionString.append(ans);
						historyRecord.add(expressionString.toString());
						statusCode=1;
					}
					else {
						statusCode=-1;
						Toast.makeText(this, "表达式有误，请仔细检查\n输入表达式有歧义", Toast.LENGTH_SHORT).show();
					}
				}
				catch(ArithmeticException e) {
					statusCode=-1;
					Toast.makeText(this, "表达式有误，请仔细检查\n除数不能为0", Toast.LENGTH_SHORT).show();
				}
				catch(Exception e) {
					statusCode=-1;
					Toast.makeText(this, "表达式有误，请仔细检查\n表达式不合法", Toast.LENGTH_SHORT).show();
				}
				break;
			default:
				break;
			}

		}
		if (v.getId() != R.id.btn_record)
			editText.setText(expressionString);
	}
	private void playSound(){
		mySoundPool.play(click, 1, 1, 0, 0, 1);
	}
	
	public boolean onKeyDown(int keyCode,KeyEvent event) {
		if(event.getAction()==KeyEvent.ACTION_DOWN && event.getKeyCode()==KeyEvent.KEYCODE_BACK) {
			
				if((System.currentTimeMillis() - exitTime) > 2000) {
					Toast.makeText(this, "再按一次退出计算器", Toast.LENGTH_SHORT).show();
					exitTime = System.currentTimeMillis();
				}
				else {
					System.exit(0);
				}
		}
		
		return true;
	}
	
}
