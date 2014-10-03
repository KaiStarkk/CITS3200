public class Test{
	
	public static void main(String[] args){

		DataSet d = new DataSet("Andrew Philpott 4721 201305031758.csv");

		Column c = d.getTimeColumn();
		Column c2 = d.getPlayerLoadColumn();
		Column c3 = d.getGPStimeColumn();
		for(int j=0;j<10;j++){
			Integer i = (Integer)c.get(10000+j);
			Double i2 = (Double)c2.get(10000+j);
			Integer i3 = (Integer)c3.get(10000+j);
			System.out.println(""+i+", "+i2+", "+i3);
		}

		

	}

}