
package services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.MiscellaneousRecordRepository;
import domain.MiscellaneousRecord;

@Service
@Transactional
public class MiscellaneousRecordService {

	// Repository-----------------------------------------------

	@Autowired
	private MiscellaneousRecordRepository			miscellaneousRecordRepository;

	// Services-------------------------------------------------

	// Constructor----------------------------------------------

	public MiscellaneousRecordService() {
		super();
	}

	// Simple CRUD----------------------------------------------

	public MiscellaneousRecord create(final String authority) {
		final MiscellaneousRecord miscellaneousRecord = new MiscellaneousRecord();
		
		return miscellaneousRecord;
	}

	public List<MiscellaneousRecord> findAll() {
		return this.miscellaneousRecordRepository.findAll();
	}

	public MiscellaneousRecord findOne(final Integer miscellaneousRecordId) {
		return this.miscellaneousRecordRepository.findOne(miscellaneousRecordId);
	}

	public MiscellaneousRecord save(final MiscellaneousRecord miscellaneousRecord) {
		Assert.notNull(miscellaneousRecord);
		final MiscellaneousRecord saved = this.miscellaneousRecordRepository.save(miscellaneousRecord);
		return saved;
	}

	public void delete(final MiscellaneousRecord miscellaneousRecord) {
		this.miscellaneousRecordRepository.delete(miscellaneousRecord);
	}

	// Other Methods--------------------------------------------
}
