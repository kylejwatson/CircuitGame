//Strategy Design Pattern
public interface PowerConsumption {
	float consumePower(float curPower);
}

class ConsumesPower implements PowerConsumption{
	public float consumePower(float curPower) {
		return curPower - 3;
	}
}
class AddsPower implements PowerConsumption{
	public float consumePower(float curPower) {
		return curPower + 10;
	}
}
class NoPower implements PowerConsumption{
	public float consumePower(float curPower) {
		return curPower;
	}
}
