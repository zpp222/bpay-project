package com.zpp.bpaybatch.processor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;

import com.zpp.bpaybatch.modle.Person;

public class PersonItemProcessor implements ItemProcessor<Person, Person> {

	private static final Logger log = LoggerFactory.getLogger(PersonItemProcessor.class);

	@Override
	public Person process(Person item) throws Exception {
		final String firstName = item.getFirstName().toUpperCase();
		final String lastName = item.getLastName().toUpperCase();
		final Person transformedPerson = new Person(firstName, lastName);
		log.info("Converting (" + item + ") into (" + transformedPerson + ")");
		return transformedPerson;
	}

}
