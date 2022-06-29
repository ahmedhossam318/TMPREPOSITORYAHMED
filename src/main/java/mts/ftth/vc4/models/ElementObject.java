package mts.ftth.vc4.models;

public class ElementObject {
	private NeGponAlarmJob gponElement;
	private NeGponCardAlarmJob cardElement;
	private NeGponPortAlarmJob portElement;
	private NeCabinetAlarmJob cabinetElement;
	private NeBoxAlarmJob boxElement;
	
	public NeGponAlarmJob getGponElement() {
		return gponElement;
	}
	public void setGponElement(NeGponAlarmJob gponElement) {
		this.gponElement = gponElement;
	}
	public NeGponCardAlarmJob getCardElement() {
		return cardElement;
	}
	public void setCardElement(NeGponCardAlarmJob cardElement) {
		this.cardElement = cardElement;
	}
	public NeGponPortAlarmJob getPortElement() {
		return portElement;
	}
	public void setPortElement(NeGponPortAlarmJob portElement) {
		this.portElement = portElement;
	}
	public NeCabinetAlarmJob getCabinetElement() {
		return cabinetElement;
	}
	public void setCabinetElement(NeCabinetAlarmJob cabinetElement) {
		this.cabinetElement = cabinetElement;
	}
	public NeBoxAlarmJob getBoxElement() {
		return boxElement;
	}
	public void setBoxElement(NeBoxAlarmJob boxElement) {
		this.boxElement = boxElement;
	}
}
