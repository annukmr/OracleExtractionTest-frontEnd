package com.iig.gcp;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import java.util.ArrayList;
import org.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.iig.gcp.extraction.oracle.dto.ConnectionMaster;
import com.iig.gcp.extraction.oracle.dto.RunFeedsBean;
import com.iig.gcp.extraction.oracle.service.ExtractionService;
import com.iig.gcp.extraction.oracle.service.ExtractionServiceImpl;


//@RunWith(SpringRunner.class)
//@SpringBootTest
//public class IigOracleApplicationTests {
//
//	@Test
//	public void contextLoads() {
//	}
//
//}

@RunWith(MockitoJUnitRunner.Silent.class)
public class IigOracleApplicationTests {
	
	@Mock
	ExtractionServiceImpl service = new ExtractionServiceImpl();
	
	@Mock
	RunFeedsBean runFeedBeans;
	ConnectionMaster connectionMaster;


	@Test
	public void TestinvokeRestSuccess() {
		ExtractionService service = mock(ExtractionService.class);
		JSONObject jsonObj;
		//String success = null;
		try {
			jsonObj = new JSONObject("{\"phonetype\":\"N95\",\"cat\":\"WP\"}");
			when(service.invokeRest(Mockito.anyString(), Mockito.anyString())).thenReturn("success");
			
			assertNotNull(service.invokeRest(jsonObj.toString(), "localhost:5770"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
		@Test
		public void TestinvokeRestFail() {
			//ExtractionService service = mock(ExtractionService.class);
			JSONObject jsonObj;
			String failString = null;
			try {
				jsonObj = new JSONObject(failString);
				assertNull(when(service.invokeRest(jsonObj.toString(), "")).thenReturn(""));
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}		
	}
		
		@Test
		public void TestGetRunFeeds() {
			
			ArrayList<String> success = new ArrayList<String>();
			ArrayList<String> fail = new ArrayList<String>();
			success.add("feed1");
			success.add("feed2");
			try {
				
				//Argument Matchers
				when(service.getRunFeeds(Mockito.anyString())).thenReturn(success);
				when(service.getRunFeeds(null)).thenReturn(fail);
				
				
				assertEquals(2,service.getRunFeeds("juniperOnprem").size());
				assertEquals(0,service.getRunFeeds(null).size());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		}
		
		@Test
		public void TestgetLastRunFeeds() {
			ArrayList<RunFeedsBean> success = new ArrayList<>();
			ArrayList<RunFeedsBean> fail = new ArrayList<>();
			success.add(runFeedBeans);
			
			try {
				
				when(service.getLastRunFeeds(Mockito.anyString(), Mockito.anyString())).thenReturn(success);
				
				when(service.getLastRunFeeds(null, null)).thenReturn(fail);
			 
						
				ArrayList<RunFeedsBean> beans = service.getLastRunFeeds("juniperOnprem", "R2D2");
			
				assertTrue(!beans.isEmpty());
				assertEquals(0, service.getLastRunFeeds(null, null).size());
				
			}catch(Exception e) {e.printStackTrace();}
		}
		
		
		@Test
		public void TestgetConnections() {
			ArrayList<ConnectionMaster> success = new ArrayList<>();
			ArrayList<ConnectionMaster> fail = new ArrayList<>();
			success.add(connectionMaster);
try {
				
				when(service.getConnections(Mockito.anyString(), Mockito.anyString())).thenReturn(success);
				when(service.getConnections(null, null)).thenReturn(fail);
				ArrayList<ConnectionMaster> beans = service.getConnections("R2D2", "juniperOnprem");
			
				assertTrue(!beans.isEmpty());
				assertEquals(0, service.getLastRunFeeds(null, null).size());
				
			}catch(Exception e) {e.printStackTrace();}
			
		}
		
		
		
		
}