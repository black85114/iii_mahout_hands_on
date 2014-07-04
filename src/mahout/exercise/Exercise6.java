package mahout.exercise;

import java.io.File;

import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.eval.RecommenderBuilder;
import org.apache.mahout.cf.taste.eval.RecommenderEvaluator;
import org.apache.mahout.cf.taste.impl.eval.AverageAbsoluteDifferenceRecommenderEvaluator;
import org.apache.mahout.cf.taste.impl.eval.RMSRecommenderEvaluator;
import org.apache.mahout.cf.taste.impl.model.file.FileDataModel;
import org.apache.mahout.cf.taste.impl.neighborhood.NearestNUserNeighborhood;
import org.apache.mahout.cf.taste.impl.recommender.GenericUserBasedRecommender;
import org.apache.mahout.cf.taste.impl.similarity.PearsonCorrelationSimilarity;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.neighborhood.UserNeighborhood;
import org.apache.mahout.cf.taste.recommender.Recommender;
import org.apache.mahout.cf.taste.similarity.UserSimilarity;
import org.apache.mahout.common.RandomUtils;

public class Exercise6 {

	public static void main(String[] args) throws Exception {
		RandomUtils.useTestSeed();
		DataModel dm = new FileDataModel(new File("data/ua.base"));
		
		RecommenderEvaluator maeEvaluator = new AverageAbsoluteDifferenceRecommenderEvaluator();
		RecommenderEvaluator rmseEvaluator = new RMSRecommenderEvaluator();
		
		RecommenderBuilder recBuilder = new RecommenderBuilder() {
			
			@Override
			public Recommender buildRecommender(DataModel dm) throws TasteException {
				UserSimilarity sim = new PearsonCorrelationSimilarity(dm);
				UserNeighborhood neighborhood = new NearestNUserNeighborhood(50, sim, dm);
				
				return new GenericUserBasedRecommender(dm, neighborhood, sim);
			}
		};
		
		double maeScore = maeEvaluator.evaluate(recBuilder, null, dm, 0.7, 1.0); // use 70% for training, 30% for testing
		double rmseScore = rmseEvaluator.evaluate(recBuilder, null, dm, 0.7, 1.0);
		System.out.println("MAE Score: " + maeScore);
		System.out.println("RMSE Score: "+ rmseScore);
	}

}
