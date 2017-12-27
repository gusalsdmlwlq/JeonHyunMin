using UnityEngine;
using System;
using System.Collections;

public class SlimeManager : MonoBehaviour {

	int month,day,hour,min;
	string slimetime;
	GameObject gamemanager;

	void Start () {
		gamemanager = GameObject.Find ("GameManager");
		month = System.DateTime.Now.Month; //월
		day = System.DateTime.Now.Day; //일
		hour = System.DateTime.Now.Hour; //시
		min = System.DateTime.Now.Minute; //분
		if (!PlayerPrefs.HasKey ("slimetime")) { //처음 시작시 슬라임 10개 지급
			GameObject.Find ("SceneLoad").GetComponent<Sceneload> ().Slimes = 10;
			PlayerPrefs.SetString ("slimetime", month + "-" + day + "-" + hour + "-" + min); //시간 갱신
			PlayerPrefs.Save ();
		}
		else if (gamemanager.GetComponent<GameManager> ().Slimes >= 10) { //슬라임개수 10개 이상
			PlayerPrefs.SetString ("slimetime", month + "-" + day + "-" + hour + "-" + min); //시간 갱신
			PlayerPrefs.Save ();
		} else { //슬라임 개수 10개 이하
			if (PlayerPrefs.HasKey ("slimetime")) {
				slimetime = PlayerPrefs.GetString ("slimetime"); //저장된 시간 불러오기
				if ((hour * 60 + min) >= (System.Convert.ToInt32(slimetime.Split ('-') [2]) * 60 + System.Convert.ToInt32(slimetime.Split ('-') [3]) + 30)) {
					//저장된 시간과 비교해 30분 이상 차이가 나는 경우
					GameObject.Find ("SceneLoad").GetComponent<Sceneload> ().Slimes = 10;
					PlayerPrefs.SetString ("slimetime", month + "-" + day + "-" + hour + "-" + min); //시간 갱신
					PlayerPrefs.Save ();
				}
				else if(month+"" != PlayerPrefs.GetString("slimetime").Split('-')[0] || day+"" != PlayerPrefs.GetString("slimetime").Split('-')[1]) {
					//날짜나 월이 바뀜
					GameObject.Find ("SceneLoad").GetComponent<Sceneload> ().Slimes = 10;
					PlayerPrefs.SetString ("slimetime", month + "-" + day + "-" + hour + "-" + min); //시간 갱신
					PlayerPrefs.Save ();
				}
			} else { //저장된 시간이 없으면 새로 저장
				PlayerPrefs.SetString ("slimetime", month + "-" + day + "-" + hour + "-" + min); //현재 시간 저장
				PlayerPrefs.Save ();
			}
		}
	}

	void Update () {
		
	}
}
