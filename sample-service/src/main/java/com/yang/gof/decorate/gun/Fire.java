package com.yang.gof.decorate.gun;

public class Fire {
	//射程
	private int range; 
	//开枪是否有声音
	private boolean voice; 
	
	@Override
	public String toString() {
		return "[射程:" + range + ","+((voice) ? "消音":"没有消音")+"]";
	}

	public int getRange() {
		return range;
	}

	public void setRange(int range) {
		this.range = range;
	}

	public boolean isVoice() {
		return voice;
	}

	public void setVoice(boolean voice) {
		this.voice = voice;
	}
	
	
}
