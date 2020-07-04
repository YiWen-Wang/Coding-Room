import { Component, OnInit, Input } from '@angular/core';
import 'codemirror/mode/javascript/javascript';
import 'codemirror/addon/fold/foldgutter';
import 'codemirror/addon/fold/brace-fold';
import 'codemirror/lib/codemirror';
import 'codemirror/addon/edit/closebrackets';
import 'codemirror/addon/edit/matchbrackets';
import 'codemirror/addon/lint/lint';
import 'codemirror/addon/lint/json-lint';
import 'codemirror/addon/hint/show-hint.js';
import 'codemirror/addon/hint/sql-hint.js';
import 'codemirror/mode/clike/clike';
import 'codemirror/mode/python/python';


@Component({
  selector: 'app-editor',
  templateUrl: './editor.component.html',
  styleUrls: [ './editor.component.scss' ]
})
export class EditorComponent implements OnInit {
  @Input() codeMirrorOptions:any;

  ngOnInit(){
  }
}


