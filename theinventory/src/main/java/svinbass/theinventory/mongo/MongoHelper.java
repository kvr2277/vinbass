package svinbass.theinventory.mongo;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import svinbass.theinventory.model.Review;
import svinbass.theinventory.mongo.SpringMongoConfig;

public class MongoHelper {
	
	private MongoOperations mongoOperation;
	
	public MongoHelper(){
		// For Annotation
				ApplicationContext ctx = 
		                     new AnnotationConfigApplicationContext(SpringMongoConfig.class);
				mongoOperation = 
		                     (MongoOperations) ctx.getBean("mongoTemplate");
				
	}
	
	public void saveReview(Review review){
		mongoOperation.save(review);
		//findCommentsByEmpId(review.getEmpId());
		
	}
	
	public List<Review> findReviewsByEmpId(int empId){
		List<Review> reviews = mongoOperation.find(
                new Query(Criteria.where("empId").is(String.valueOf(empId))),
                Review.class);
		
		for (Review review : reviews) {
			System.out.println(review);			
		}

		return reviews;
	}
	
	public List<Review> findReviews(){
		Query query = new Query();
		query.addCriteria(Criteria.where("rating").gte(3));
		List<Review> reviews = mongoOperation.find(query,
                Review.class);
		
		for (Review review : reviews) {
			System.out.println(review);			
		}

		return reviews;
	}
	
	
	
	
}
