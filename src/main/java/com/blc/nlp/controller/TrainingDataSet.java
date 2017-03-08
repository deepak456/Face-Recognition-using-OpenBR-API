package com.bls.TESTNLP;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.zip.GZIPInputStream;

import opennlp.maxent.GIS;
import opennlp.maxent.io.GISModelReader;
import opennlp.maxent.io.SuffixSensitiveGISModelWriter;
import opennlp.model.AbstractModel;
import opennlp.model.AbstractModelWriter;
import opennlp.model.DataIndexer;
import opennlp.model.DataReader;
import opennlp.model.FileEventStream;
import opennlp.model.MaxentModel;
import opennlp.model.OnePassDataIndexer;
import opennlp.model.PlainTextFileDataReader;

public class TrainingDataSet {

	public static void main(String[] args) throws Exception {
		String trainingDataFile = "/home/deepak/Desktop/model File/data.csv";
		String outputModelFile = "/home/deepak/Desktop/model File/data.maxent.gz";
		String sentence = "how many beds are in the hospital";
		String stringArray[] =  sentence.split(" ");
		train(trainingDataFile, outputModelFile);
		predict(stringArray, outputModelFile);
	}

//Function for train data using maxent Api
	private static void train(String trainingDataFile, String outputModelFile, String tagToFind) {
		Charset charset = Charset.forName("UTF-8");
		try {
            DataIndexer indexer = new OnePassDataIndexer(new FileEventStream(
            		trainingDataFile));
            MaxentModel trainedMaxentModel = GIS.trainModel(100, indexer);
            File outFile = new File(outputModelFile);
            AbstractModelWriter writer = new SuffixSensitiveGISModelWriter(
                    (AbstractModel) trainedMaxentModel, outFile);
            writer.persist();
            System.out.println("done");
        } catch (Exception e) {
            e.printStackTrace();
        }

	}

	private static void predict(String[] sentence, String modelFile) throws Exception {
		FileInputStream inputStream;
    try {
        inputStream = new FileInputStream(modelFile);
        InputStream decodedInputStream = new GZIPInputStream(inputStream);
        DataReader modelReader = new PlainTextFileDataReader(
                decodedInputStream);
        MaxentModel loadedMaxentModel = new GISModelReader(modelReader)
                .getModel();
       
       // String[] context = TestNlp.finalTokens("14C RECLINE BUTTON DONT WORK RESECURED SEAT 14C RECLINE CABLE IAW STAND MAINT PRACTICES");
       
    
        double[] outcomeProbs = loadedMaxentModel.eval(sentence);
      
       
        String outcome = loadedMaxentModel.getBestOutcome(outcomeProbs);
        
        System.out.println(outcome);
    }catch(Exception e){
    	e.printStackTrace();
    }
	}
}