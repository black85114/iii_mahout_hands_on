package mahout.exercise;

import java.io.File;
import java.util.List;

import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.eval.IRStatistics;
import org.apache.mahout.cf.taste.eval.RecommenderBuilder;
import org.apache.mahout.cf.taste.eval.RecommenderIRStatsEvaluator;
import org.apache.mahout.cf.taste.impl.eval.GenericRecommenderIRStatsEvaluator;
import org.apache.mahout.cf.taste.impl.model.file.FileDataModel;
import org.apache.mahout.cf.taste.impl.recommender.GenericItemBasedRecommender;
import org.apache.mahout.cf.taste.impl.similarity.PearsonCorrelationSimilarity;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;
import org.apache.mahout.cf.taste.recommender.Recommender;
import org.apache.mahout.cf.taste.similarity.ItemSimilarity;
import org.apache.mahout.common.RandomUtils;

public class Exercise8 {

	public static void main(String[] args) throws Exception {
		RandomUtils.useTestSeed(); 
		DataModel dm = new FileDataModel(new File("data/ua.base")); 
		RecommenderIRStatsEvaluator irEvaluator = new GenericRecommenderIRStatsEvaluator(); 
		
		
		RecommenderBuilder recBuilder = new RecommenderBuilder() {
			
			@Override
			public Recommender buildRecommender(DataModel dm) throws TasteException {
				ItemSimilarity sim = new PearsonCorrelationSimilarity(dm);
				
				return new GenericItemBasedRecommender(dm, sim); 
			}
		};
		
		IRStatistics irStat = irEvaluator.evaluate(recBuilder, null, dm, null, 5, GenericRecommenderIRStatsEvaluator.CHOOSE_THRESHOLD, 1.0);
		System.out.println(irStat);
		
		List<RecommendedItem> recommendations = recBuilder.buildRecommender(dm).recommend(1, 5);
		System.out.println(recommendations);
	}
}
