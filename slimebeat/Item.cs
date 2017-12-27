using UnityEngine;
using System;
using System.Collections;
using System.Collections.Generic;

public class Item : MonoBehaviour {

	private string item_ = null; //아이템 종류(item_1, item_2...)
	public string number; //아이템창 칸의 번호(1,2,3)
	GameObject gamemanager;

	void Start () {
		gamemanager = GameObject.Find ("GameManager");
	}

	void Update () {
		if (Input.touchCount > 0) {
			if (Input.touchCount == 1) { //터치가 하나일 때
				if (Input.GetTouch (0).phase == TouchPhase.Began) { //처음 눌린 상태인지 검사
					Vector3 pos = Camera.main.ScreenToWorldPoint (Input.GetTouch (0).position);
					Vector2 touchpos = new Vector2 (pos.x, pos.y);
					if (GetComponent<Collider2D> () == Physics2D.OverlapPoint (touchpos)) {
						Touched (); //터치확인
					}
				}
			} 
		}
	}

	void Touched()
	{
		switch (gameObject.name) {
		case "item-1": //첫번째칸
		case "item-2": //두번째칸
		case "item-3": //세번째칸
			GameObject.Find("SoundManager").GetComponent<SoundManager>().Playsound("button");
			switch (item_) { //아이템칸이 비어있는지 아닌지 검사
			case "noitem": //아이템칸이 빈 경우
				GameObject ins = Instantiate (Resources.Load ("Prefabs/itemlist"), new Vector3 (0, 0, -4), transform.rotation) as GameObject; //아이템 리스트 표시
				string[] name = gameObject.name.Split ('-'); //아이템칸의 번호확인
				ins.GetComponent<Item> ().number = name [1]; //아이템 리스트에 아이템칸의 번호 전달
				List<string> items = gamemanager.GetComponent<GameManager> ().Items; //저장된 아이템 목록을 불러옴
				if (items.Count > 0) { //아이템 목록에 아이템이 있을 경우
					for (int i = 0; i < items.Count; i++) { //아이템 리스트에 목록에 저장된 아이템들을 표시
						if (i % 3 == 0) {
							GameObject insitem = Instantiate (Resources.Load ("Prefabs/item"), new Vector3 (-3.5f, 7 - (int)(i / 3) * 3, -5), transform.rotation) as GameObject;
							insitem.GetComponent<Item> ().Item_ = items [i]; //아이템 종류 저장
							insitem.transform.parent = ins.transform; //표시한 아이템을 아이템 리스트의 자식으로 지정
							insitem.GetComponent<Item> ().number = ins.GetComponent<Item> ().number; //표시한 아이템에 아이템칸의 번호 전달
						} else if (i % 3 == 1) {
							GameObject insitem = Instantiate (Resources.Load ("Prefabs/item"), new Vector3 (0, 7 - (int)(i / 3) * 3, -5), transform.rotation) as GameObject;
							insitem.GetComponent<Item> ().Item_ = items [i];
							insitem.transform.parent = ins.transform;
							insitem.GetComponent<Item> ().number = ins.GetComponent<Item> ().number;
						} else if (i % 3 == 2) {
							GameObject insitem = Instantiate (Resources.Load ("Prefabs/item"), new Vector3 (3.5f, 7 - (int)(i / 3) * 3, -5), transform.rotation) as GameObject;
							insitem.GetComponent<Item> ().Item_ = items [i];
							insitem.transform.parent = ins.transform;
							insitem.GetComponent<Item> ().number = ins.GetComponent<Item> ().number;
						}
					}
				}
				break;
			default: //아이템칸에 이미 아이템이 장착된 경우
				GameObject ins_ = Instantiate (Resources.Load ("Prefabs/" + item_ + "_info"), new Vector3 (0, 0, -2), transform.rotation) as GameObject; //장착한 아이템 정보 표시
				string[] name_ = gameObject.name.Split ('-'); //아이템칸의 번호 확인
				ins_.GetComponent<Item> ().number = name_ [1]; //아이템칸의 번호 저장
				break;
			}
			break;
		case "item(Clone)": //아이템 리스트에 표시된 아이템 클릭
			GameObject.Find("SoundManager").GetComponent<SoundManager>().Playsound("button");
			GameObject ins_1 = Instantiate (Resources.Load ("Prefabs/" + item_ + "_change"), new Vector3 (0, 0, -6), transform.rotation) as GameObject; //아이템 변경 창 표시
			ins_1.GetComponent<Item> ().number = number; //아이템 변경 창에 아이템칸의 번호 전달
			Destroy (gameObject); 
			Destroy (transform.parent.gameObject); //아이템 리스트 제거
			break;
		case "select": //아이템 선택(새로운 아이템 장착)
			GameObject.Find("SoundManager").GetComponent<SoundManager>().Playsound("button");
			for (int i = 1; i < 4; i++) { //3개의 아이템칸을 모두 검사
				if (GameObject.Find ("item-" + i).GetComponent<Item> ().Item_ == "item_" + transform.parent.name.Split ('_') [1]) { //선택한 아이템을 이미 장착하고 있는 경우
					if ("" + i == transform.parent.GetComponent<Item> ().number) { //아이템 해제(같은 아이템 한번 더 선택)
						GameObject.Find ("item-" + transform.parent.GetComponent<Item> ().number).GetComponent<Item> ().Item_ = "noitem"; //해당 아이템칸을 빈 칸으로 표시
						gamemanager.GetComponent<GameManager> ().equipment [System.Convert.ToInt32(transform.parent.GetComponent<Item> ().number) - 1] = "noitem";
						PlayerPrefs.SetString ("equipment" + (System.Convert.ToInt32(transform.parent.GetComponent<Item> ().number) - 1), "noitem");
						PlayerPrefs.Save ();
					}
					Destroy (gameObject);
					Destroy (transform.parent.gameObject); //아이템 변경 창 제거
					return;
				}
			} //해당 아이템을 장착하고 있지 않은 경우
			GameObject.Find ("item-" + transform.parent.GetComponent<Item> ().number).GetComponent<Item> ().Item_ = "item_" + transform.parent.name.Split ('_') [1]; //해당 아이템칸에 선택한 아이템 저장
			gamemanager.GetComponent<GameManager> ().equipment [System.Convert.ToInt32(transform.parent.GetComponent<Item> ().number) - 1] = "item_" + transform.parent.name.Split ('_') [1];
			PlayerPrefs.SetString ("equipment" + (System.Convert.ToInt32(transform.parent.GetComponent<Item> ().number) - 1), "item_" + transform.parent.name.Split ('_') [1]);
			PlayerPrefs.Save ();
			Destroy (gameObject);
			Destroy (transform.parent.gameObject); //아이템 변경 창 제거
			break;
		case "change": //아이템 정보창에서 '아이템 변경'
			GameObject.Find("SoundManager").GetComponent<SoundManager>().Playsound("button");
			GameObject _ins = Instantiate (Resources.Load ("Prefabs/itemlist"), new Vector3 (0, 0, -4), transform.rotation) as GameObject; //아이템 리스트 표시
			_ins.GetComponent<Item> ().number = transform.parent.GetComponent<Item> ().number; //아이템 리스트에 아이템칸의 번호 전달
			List<string> _items = gamemanager.GetComponent<GameManager> ().Items;
			if (_items.Count > 0) {
				for (int i = 0; i < _items.Count; i++) {
					if (i % 3 == 0) {
						GameObject insitem = Instantiate (Resources.Load ("Prefabs/item"), new Vector3 (-3.5f, 7 - (int)(i / 3) * 3, -5), transform.rotation) as GameObject;
						insitem.GetComponent<Item> ().Item_ = _items [i];
						insitem.transform.parent = _ins.transform;
						insitem.GetComponent<Item> ().number = _ins.GetComponent<Item> ().number;
					} else if (i % 3 == 1) {
						GameObject insitem = Instantiate (Resources.Load ("Prefabs/item"), new Vector3 (0, 7 - (int)(i / 3) * 3, -5), transform.rotation) as GameObject;
						insitem.GetComponent<Item> ().Item_ = _items [i];
						insitem.transform.parent = _ins.transform;
						insitem.GetComponent<Item> ().number = _ins.GetComponent<Item> ().number;
					} else if (i % 3 == 2) {
						GameObject insitem = Instantiate (Resources.Load ("Prefabs/item"), new Vector3 (3.5f, 7 - (int)(i / 3) * 3, -5), transform.rotation) as GameObject;
						insitem.GetComponent<Item> ().Item_ = _items [i];
						insitem.transform.parent = _ins.transform;
						insitem.GetComponent<Item> ().number = _ins.GetComponent<Item> ().number;
					}
				}
			}
			Destroy (gameObject);
			Destroy (transform.parent.gameObject); //아이템 리스트 제거
			break;
		case "cancle": //'취소'
			GameObject.Find("SoundManager").GetComponent<SoundManager>().Playsound("button");
			Destroy (gameObject);
			Destroy (transform.parent.gameObject); //해당 창 제거
			break;
		}
	}

	public string Item_ //아이템칸, 리스트에 표시된 아이템에 저장된 아이템 종류
	{
		get {
			return item_;
		}
		set {
			item_ = value;
			if (item_ != "noitem") { //아이템 종류가 저장되면 이미지 변경
				gameObject.GetComponent<SpriteRenderer> ().sprite = (Sprite)Resources.Load ("Images/" + item_, typeof(Sprite));
			} else {
				gameObject.GetComponent<SpriteRenderer> ().sprite = (Sprite)Resources.Load ("Images/item", typeof(Sprite));
			}
		}
	}
}
