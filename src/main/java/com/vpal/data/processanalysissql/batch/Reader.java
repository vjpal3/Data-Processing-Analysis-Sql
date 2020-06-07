package com.vpal.data.processanalysissql.batch;

import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.Collections;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.separator.DefaultRecordSeparatorPolicy;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.core.io.ClassPathResource;

import com.vpal.data.processanalysissql.model.ConsumerComplaint;

public class Reader {
	
	 private static final Logger log = LoggerFactory.getLogger(Reader.class);
	
	public static FlatFileItemReader<ConsumerComplaint> reader(String path) {
	    FlatFileItemReader<ConsumerComplaint> reader = new FlatFileItemReader<ConsumerComplaint>();
	 
	    reader.setResource(new ClassPathResource(path));
	    reader.setRecordSeparatorPolicy(new DefaultRecordSeparatorPolicy());
	    reader.setLinesToSkip(1);
	    reader.setLineMapper(new DefaultLineMapper<ConsumerComplaint>() {
	      {
	        setLineTokenizer(new DelimitedLineTokenizer() {
	          {
	            setNames(new String[] { "dateReceived", "productName", "subProduct", "issue", "subIssue", "consumerComplaintNarrative", "companyPublicResponse", 
	            		"company", "stateName", "zipCode", "tags", "consumerConsentProvided", "submittedVia", "dateSent", "companyResponseToConsumer", 
	            		"timelyResponse", "consumerDisputed", "complaintId" 
	            });
	            setStrict(false);
	          }
	        });
	        
	        setFieldSetMapper(new BeanWrapperFieldSetMapper<ConsumerComplaint>() {
	          {
	            setTargetType(ConsumerComplaint.class);
	            
	            setCustomEditors(Collections.singletonMap(Date.class,
	                    new CustomDateEditor(new SimpleDateFormat("MM/dd/yyyy"), false)));
	          }
	        });
	      }
	    });
	    return reader;
	  }
}
