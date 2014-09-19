package StintAnalyser.Analysis.Heuristics;

import StintAnalyser.Data.Column;
import StintAnalyser.Data.DataSet;
import StintAnalyser.Stints.Stint;
import StintAnalyser.Stints.StintSet;

/**
 * CITS3200 Professional Computing AccelerometerHeuristic.java Determines if a
 * player is playing on not depending on their accelerometer data.
 *
 * @version 1.1 20/08/14
 * @author Group B
 */
public class PlayerLoadAnalyser {
        DataSet dataSet;
    
        public PlayerLoadAnalyser(DataSet dataSet) {
            this.dataSet = dataSet;
        }

	/**
	 * Determine if the player is playing or not.
	 *
	 * @return
	 */
	public StintSet findStints() {

		//experimental number will be changed to a calculated number
		double sig_activity = 10;
		boolean found_start = false;

		Column time = dataSet.getGPStimeColumn();
		Column accel = dataSet.getPlayerLoadColumn();
		StintSet solution = new StintSet();

		//check correct data
		if (time.length() != accel.length()) {
			return null;

		}
		double estimated_start = 0;

		int ticks = 0;
		int tick_threshold_start = 100;
		int tick_threshold_end = 100;
		int stint_number = 1;

		for (int i = 0; i < time.length(); i++) {

			double c_time = (double) time.get(i);
			double c_accel = (double) accel.get(i);

			if (found_start) {
				if (c_accel < sig_activity) {

					ticks++;
					if (ticks > tick_threshold_end) {

						double end_time = (double) time.get(i - 100);
						//solution.addStint(new Stint(estimated_start, end_time, 0, 0, 0));
					}
				} else {

					ticks = 0;
				}

			} else {

				//continue
			}

		}

		return new StintSet();
	}

}
