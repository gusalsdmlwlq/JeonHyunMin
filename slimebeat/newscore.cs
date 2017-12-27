using UnityEngine;
using System.Collections;

public class newscore : MonoBehaviour {

	GameObject result;

	void Start () {
		result = GameObject.Find ("result(Clone)");
		gameObject.transform.parent = result.transform;
		switch (gameObject.name) {
		case "clear(Clone)":
			GameObject.Find ("SoundManager").GetComponent<SoundManager> ().Playsound ("clear");
			break;
		case "fail(Clone)":
			GameObject.Find ("SoundManager").GetComponent<SoundManager> ().Playsound ("fail");
			break;
		}
	}

	void Update () {
	
	}
}
