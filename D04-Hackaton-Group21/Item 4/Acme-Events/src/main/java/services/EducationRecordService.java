
package services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.EducationRecordRepository;
import domain.EducationRecord;

@Service
@Transactional
public class EducationRecordService {

	// Repository-----------------------------------------------

	@Autowired
	private EducationRecordRepository			educationRecordRepository;

	// Services-------------------------------------------------

	// Constructor----------------------------------------------

	public EducationRecordService() {
		super();
	}

	// Simple CRUD----------------------------------------------

	public EducationRecord create(final String authority) {
		final EducationRecord educationRecord = new EducationRecord();
		
		return educationRecord;
	}

	public List<EducationRecord> findAll() {
		return this.educationRecordRepository.findAll();
	}

	public EducationRecord findOne(final Integer educationRecordId) {
		return this.educationRecordRepository.findOne(educationRecordId);
	}

	public EducationRecord save(final EducationRecord educationRecord) {
		Assert.notNull(educationRecord);
		final EducationRecord saved = this.educationRecordRepository.save(educationRecord);
		return saved;
	}

	public void delete(final EducationRecord educationRecord) {
		this.educationRecordRepository.delete(educationRecord);
	}

	// Other Methods--------------------------------------------
}
