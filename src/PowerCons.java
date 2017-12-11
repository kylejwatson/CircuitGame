//Strategy Design Pattern
public interface PowerCons {
	float consumePower(float curPower);
}

class ConsumesPower implements PowerCons{
	public float consumePower(float curPower) {
		return curPower - 3;
	}
}
class AddsPower implements PowerCons{
	public float consumePower(float curPower) {
		return curPower + 10;
	}
}
class NoPower implements PowerCons{
	public float consumePower(float curPower) {
		return curPower;
	}
}
