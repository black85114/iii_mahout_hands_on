package mahout.exercise;

import java.io.File;
import java.util.List;

import org.apache.mahout.cf.taste.impl.model.file.FileDataModel;
import org.apache.mahout.cf.taste.impl.neighborhood.NearestNUserNeighborhood;
import org.apache.mahout.cf.taste.impl.neighborhood.ThresholdUserNeighborhood;
import org.apache.mahout.cf.taste.impl.recommender.GenericUserBasedRecommender;
import org.apache.mahout.cf.taste.impl.similarity.EuclideanDistanceSimilarity;
import org.apache.mahout.cf.taste.impl.similarity.PearsonCorrelationSimilarity;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.neighborhood.UserNeighborhood;
import org.apache.mahout.cf.taste.recommender.*;
import org.apache.mahout.cf.taste.similarity.UserSimilarity;

public class Exercise4 {

	public static void main(String[] args) throws Exception {
		DataModel dm = new FileDataModel(new File("data/intro.csv"));

		UserSimilarity sim = new PearsonCorrelationSimilarity(dm);
		//UserSimilarity sim = new EuclideanDistanceSimilarity(dm);
		
		UserNeighborhood neighborhood = new NearestNUserNeighborhood(2, sim, dm);  //2-nearest User
		//UserNeighborhood neighborhood = new ThresholdUserNeighborhood(0.1, pearson, dm);
		
		Recommender recommender = new GenericUserBasedRecommender(dm, neighborhood, sim);
		
		List<RecommendedItem> recommendations = recommender.recommend(1, 1); //recommender User with ID: 1 the top-1 item
		System.out.println(recommendations);
	}

}
