package calc;

public class Kalkuluesi {

	public double kalkuloPerqindjen(double rabati, double vlera) {
		return ((100 - rabati) / 100) * vlera;
	}

	public double kalkuloVleren(double rabati, double vleraPasZbritjes) {
		return (vleraPasZbritjes / (1 - (rabati / 100)));
	}
}