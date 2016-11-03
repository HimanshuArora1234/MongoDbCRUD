package fr.him.mongoSample;

import com.mongodb.MongoClient;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.DBCursor;


/**
 * A CRUD Sample of mongodb with java.
 * Don't forget to start mongod sever before running this app.
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    	 // To connect to mongodb server
        MongoClient mongoClient = new MongoClient( "localhost" , 27017 );
        
        // Now connect to your database, let's say xyz
        DB db = mongoClient.getDB( "xyz" );
        System.out.println("Connect to database successfully");
        
        //Create a collection or table with reference to rdbms
        DBCollection coll = db.createCollection("collection1", new BasicDBObject());
        System.out.println("Collection created successfully");
        
        //Get the collection
        coll = db.getCollection("collection1");
        System.out.println("Collection collection1 selected successfully");
        
        //Insert a document
        BasicDBObject doc = new BasicDBObject("title", "MongoDB").
                append("description", "Mongo example").
                append("likes", 100).
                append("url", "https://github.com/HimanshuArora1234").
                append("by", "Himanshu Arora");
        coll.insert(doc);
        System.out.println("Document inserted successfully");
        
        //Query all data of the collection1
        DBCursor cursor = coll.find();
        int i = 1;
        while (cursor.hasNext()) { 
           System.out.println("Inserted Document: "+i); 
           System.out.println(cursor.next()); 
           i++;
        }
        
        //Update document
        cursor = coll.find();
        while (cursor.hasNext()) { 
           DBObject updateDocument = cursor.next();
           updateDocument.put("likes","400");
           coll.update(new BasicDBObject(), updateDocument); 
        }
			
        System.out.println("Document updated successfully");
        cursor = coll.find();
        i = 1;
        while (cursor.hasNext()) { 
           System.out.println("Updated Document: "+i); 
           System.out.println(cursor.next()); 
           i++;
        }
        
        //Delete document
        DBObject myDoc = coll.findOne();
        coll.remove(myDoc);
        cursor = coll.find();
        i = 1;	
        while (cursor.hasNext()) { 
           System.out.println("Inserted Document: "+i); 
           System.out.println(cursor.next()); 
           i++;
        }
        System.out.println("Document deleted successfully");
			
    }
}
