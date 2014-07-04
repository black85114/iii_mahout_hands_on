package mahout.exercise;


import java.io.File;

import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.eval.*;
import org.apache.mahout.cf.taste.impl.eval.GenericRecommenderIRStatsEvaluator;
import org.apache.mahout.cf.taste.impl.model.file.FileDataModel;
import org.apache.mahout.cf.taste.impl.neighborhood.NearestNUserNeighborhood;
import org.apache.mahout.cf.taste.impl.recommender.GenericUserBasedRecommender;
import org.apache.mahout.cf.taste.impl.similarity.PearsonCorrelationSimilarity;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.neighborhood.UserNeighborhood;
import org.apache.mahout.cf.taste.recommender.Recommender;
import org.apache.mahout.cf.taste.similarity.UserSimilarity;
import org.apache.mahout.common.RandomUtils;

public class Exercise7 {

	public static void main(String[] args) throws Exception{

	RandomUtils.useTestSeed();
	DataModel dm = new FileDataModel(new File("data/ua.base"));
	
	RecommenderIRStatsEvaluator irEvaluator = new GenericRecommenderIRStatsEvaluator(); 

	RecommenderBuilder recBuilder = new RecommenderBuilder() {
		
		@Override
		public Recommender buildRecommender(DataModel dm) throws TasteException {
			UserSimilarity sim = new PearsonCorrelationSimilarity(dm);
			UserNeighborhood neighborhood = new NearestNUserNeighborhood(500, sim, dm);
			
			return new GenericUserBasedRecommender(dm, neighborhood, sim);
		}
	};

	IRStatistics irStat = irEvaluator.evaluate(recBuilder, null, dm, null, 5, GenericRecommenderIRStatsEvaluator.CHOOSE_THRESHOLD, 1.0);
	System.out.println(irStat.getPrecision());
	System.out.println(irStat.getRecall());
	System.out.println(irStat.getF1Measure());
	}
}
