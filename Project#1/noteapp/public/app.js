/**
 *
 * Copyright (c) 2017 Maximilian Wendel
 * JavaScript (offline) Note App
 *
 */

// Get element by ID in a cleaner way
function select(id) {
  return document.getElementById(id);
}

// Save data
function save(data) {
  var toWrite = [];
  var stored = JSON.parse(localStorage.getItem("data"));
  if (stored != null) {
    for (i = 0; i < stored.length; i++) {
      toWrite.push(stored[i]);
    }
  }
  toWrite.push(data);
  localStorage.setItem("data", JSON.stringify(toWrite));
}

// Get data
function get() {
  if (localStorage.getItem("data") == null) {
    localStorage.setItem("data", "[]");
  }
  return JSON.parse(localStorage.getItem("data"));
}

// Check browser compatability
function checkCompatability() {
  if (typeof(Storage) !== "undefined") {
    loadNotes();
  } else {
      alert("Sorry, your web browser does not support local storage.");
  }
}

/**
 *
 * Note functions
 *
 */

// Create new note
function newNote() {
  html = "";
  html += "<div class=\"note\">\n"
  html += "<input type=\"text\" id=\"title\" placeholder=\"Title...\">\n<br />\n";
  html += "<textarea type=\"text\" id=\"content\" placeholder=\"Content...\"></textarea>\n<br />\n";
  html += "<button onclick=\"saveNewNote();\">Save note</button>\n";
  html += "<button onclick=\"cancelNewNote();\">Cancel</button>\n";
  html += "</div>"
  select("action").innerHTML = html;
}

// Save new note
function saveNewNote() {
  var title = select("title").value;
  var content = select("content").value;
  var note = {
    "title": title,
    "content": content
  }
  save(note);
  select("action").innerHTML = "";
  loadNotes();
}

function loadNotes() {
  var data = get();
  if (data != "") {
    select("notes").innerHTML = "";
    for (i = 0; i < data.length; i++) {
      html = "";
      html += "<div class=\"note\">";
      html += "<h3>" + data[i].title + "</h3>\n";
      html += "<p>" + data[i].content + "</p>\n";
      html += "<button onclick=\"editNote(" + i.toString() + ");\">Edit note [ .. ]</button>\n";
      html += "<button class=\"red\" onclick=\"deleteNote(" + i.toString() + ")\">Delete note [ - ]</button>\n";
      html += "</div>\n";

      select("notes").innerHTML += html;
    }
  } else {
    select("notes").innerHTML = "";
    select("notes").innerHTML += "<h2>No notes found.</h2>";
  }
}

// Export notes
function exportNotes() {
  var data = get();
  select("export").innerHTML = JSON.stringify(data, null, 2);
}

// Cancel new note
function cancelNewNote() {
  select("action").innerHTML = "";
}

// Delete note
function deleteNote(id) {
  var toWrite = [];
  var stored = JSON.parse(localStorage.getItem("data"));
  if (stored != null) {
    for (i = 0; i < stored.length; i++) {
      if (i != id) {
        toWrite.push(stored[i]);
      }
    }
  }
  localStorage.setItem("data", JSON.stringify(toWrite));
  loadNotes();
}

// Edit note
function editNote(id) {
  var data = get();
  html = "";
  html += "<div class=\"note\">\n";
  html += "<input type=\"text\" id=\"title\" placeholder=\"Title...\" value=\"" + data[id].title + "\">\n<br />\n";
  html += "<textarea type=\"text\" id=\"content\" placeholder=\"Content...\">" + data[id].content + "</textarea>\n<br />\n";
  html += "<button onclick=\"saveNewNote();\">Save note</button>\n";
  html += "</div>";
  select("action").innerHTML = html;
  deleteNote(id);
}
