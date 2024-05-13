package ec.com.technoloqie.message.api.dao.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ec.com.technoloqie.message.api.commons.log.MessageLog;
import ec.com.technoloqie.message.api.dao.IIntentDao;
import ec.com.technoloqie.message.api.dao.IProcessChatDao;
import ec.com.technoloqie.message.api.dto.ChatDto;
import ec.com.technoloqie.message.api.model.Intent;



@Repository
public class ProcessChatDaoImpl implements IProcessChatDao{
	
	@Autowired
	private EntityManager em;
	
	@Autowired
	private IIntentDao intentDao;
	
	
	public Intent getIntent(String text) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT * FROM SCBTFAQSTAGED WHERE QUERY LIKE CONCAT('%',:text,'%')");
		Query query = em.createNativeQuery(sql.toString());
		query.setParameter("text",text);
		List<Object[]> list = query.getResultList();
		MessageLog.getLog().info("Ingreso a consultar intencion"+ list.size());
		Collection <Intent>intenLst = new ArrayList<Intent>();
		if (list != null) {
			list.stream().map((row) -> {
				MessageLog.getLog().info("en el map" + row[0] + row[1]);
				//stockScanDtolst.put(row[0].toString(), Double.parseDouble(row[1].toString()));
				//StockCountScanDto stockScan = new StockCountScanDto();
				Intent intent = new Intent();
				//stockScan.setId(Integer.parseInt(row[0].toString()));
				//stockScan.setProductCode(row[0].toString());
				//stockScan.setUserScannedQty(Double.parseDouble(row[1].toString()));
				intent.setId(Integer.parseInt(row[0].toString()));
				intent.setName(row[1].toString());
				intent.setQuery(row[2].toString());
				intent.setResponse(row[3].toString());
				intenLst.add(intent);
				return intent;
			}).collect(Collectors.toList());
		}
		
		Intent intentres = intenLst.iterator().next();
		
		return intentres;
	}


	@Override
	public ChatDto getResponseChat(ChatDto chat) {
		MessageLog.getLog().info("Ingreso a consultar chat");
		Intent intent = this.intentDao.findById(10).orElse(null);
		Intent intent1 = getIntent(chat.getText());
		chat.setResponse(intent1.getResponse());
		return chat;
	}


}
