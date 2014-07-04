package mahout.exercise;

import java.io.File;

import org.apache.mahout.cf.taste.impl.model.file.FileDataModel;
import org.apache.mahout.cf.taste.impl.similarity.EuclideanDistanceSimilarity;
import org.apache.mahout.cf.taste.impl.similarity.PearsonCorrelationSimilarity;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.similarity.UserSimilarity;

public class Exercise3 {

	public static void main(String[] args) throws Exception {

		DataModel dm = new FileDataModel(new File("data/ua.base"));
		System.out.println("Number of users: " + dm.getNumUsers());
		
		UserSimilarity pearson = new PearsonCorrelationSimilarity(dm);
		UserSimilarity euclidean = new EuclideanDistanceSimilarity(dm);

		System.out.println("Pearson:" + pearson.userSimilarity(1, 2));
		System.out.println("Euclidean:" + euclidean.userSimilarity(1, 2));
		System.out.println("Pearson:" + pearson.userSimilarity(1, 3));
		System.out.println("Euclidean:" + euclidean.userSimilarity(1, 3));

	}

}
