using UnityEngine;
using System.Collections;

public class SoundManager : MonoBehaviour {

	public AudioClip slime, pop, clear, fail, button, damage;

	void Start () {
		
	}

	void Update () {
	
	}

	public void Playsound(string sound)
	{
		if (GameObject.Find ("GameManager").GetComponent<GameManager> ().sound == true) {
			switch (sound) {
			case "slime":
				GetComponent<AudioSource> ().clip = slime;
				break;
			case "pop":
				GetComponent<AudioSource> ().clip = pop;
				break;
			case "clear":
				GetComponent<AudioSource> ().clip = clear;
				break;
			case "fail":
				GetComponent<AudioSource> ().clip = fail;
				break;
			case "button":
				GetComponent<AudioSource> ().clip = button;
				break;
			case "damage":
				GetComponent<AudioSource> ().clip = damage;
				break;
			}
			GetComponent<AudioSource> ().Play ();
		}
	}
}
