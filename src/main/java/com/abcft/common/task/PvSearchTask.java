package com.abcft.common.task;

import java.util.List;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.abcft.system.pvSearch.HttpClient;
import com.abcft.system.pvSearch.PvSearchDao;
import com.abcft.system.pvSearch.PvSearchInfo;

@Component
public class PvSearchTask {
	
	@Autowired
	private PvSearchDao pvSearchDao;
	
	private static final String url = "http://sso.aqlicai.cn/user/getuser";

	//访问来源分析
	@Scheduled(cron = "0 0/10 * * * ?")
	public void run() {
		uploadPvSearchUserName();
	}
	
	private void uploadPvSearchUserName() {
		List<PvSearchInfo> list = pvSearchDao.findList();
		for (PvSearchInfo pvSearchInfo : list) {
			try {
				String result = HttpClient.doPost(url, "uid=" + pvSearchInfo.getUserId());
				System.out.println(result);
				if (result != null && result.length() > 0) { 
					JSONObject obj = JSONObject.fromObject(result);
					if (obj.getString("code") != null && obj.getString("code").equals("1")) {
						continue;
					} else if (obj.getString("code") != null && obj.getString("code").equals("0")) {
						JSONObject data = obj.getJSONObject("data");
						JSONObject user = data.getJSONObject("user");
						String userName = user.getString("account");
						
						int result2 = pvSearchDao.updatePvSearchByUserId(pvSearchInfo.getUserId(), userName);
						System.out.println(result2);
					}
				}
			} catch (Exception e) {
				System.out.println("fail");
			}
		}
	}
	
}
