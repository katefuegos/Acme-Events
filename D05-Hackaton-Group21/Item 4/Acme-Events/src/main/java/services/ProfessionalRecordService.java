
package services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.ProfessionalRecordRepository;
import domain.ProfessionalRecord;

@Service
@Transactional
public class ProfessionalRecordService {

	// Repository-----------------------------------------------

	@Autowired
	private ProfessionalRecordRepository			professionalRecordRepository;

	// Services-------------------------------------------------

	// Constructor----------------------------------------------

	public ProfessionalRecordService() {
		super();
	}

	// Simple CRUD----------------------------------------------

	public ProfessionalRecord create(final String authority) {
		final ProfessionalRecord professionalRecord = new ProfessionalRecord();
		
		return professionalRecord;
	}

	public List<ProfessionalRecord> findAll() {
		return this.professionalRecordRepository.findAll();
	}

	public ProfessionalRecord findOne(final Integer professionalRecordId) {
		return this.professionalRecordRepository.findOne(professionalRecordId);
	}

	public ProfessionalRecord save(final ProfessionalRecord professionalRecord) {
		Assert.notNull(professionalRecord);
		final ProfessionalRecord saved = this.professionalRecordRepository.save(professionalRecord);
		return saved;
	}

	public void delete(final ProfessionalRecord professionalRecord) {
		this.professionalRecordRepository.delete(professionalRecord);
	}

	// Other Methods--------------------------------------------
}
