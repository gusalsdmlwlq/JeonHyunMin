using UnityEngine;
using System.Collections;

public class Slime_pop : MonoBehaviour {

	void Start () {
		GameObject.Find("SoundManager").GetComponent<SoundManager>().Playsound("pop");
		Destroy (gameObject, 0.5f);
	}

	void Update () {
	
	}
}
