import React, { Component } from 'react';
import Formularz from './Form'
import InputLabel from './fieldTypes/InputLabel'
import Input from './fieldTypes/Input'
import SelectlistLabel from './fieldTypes/SelectlistLabel'
import Schedule from './Schedule'


export default class App extends Component {
  render() {
    let pola = [
      { typ: InputLabel, id: "kwota_kredytu", text: "Kwota kredytu"},
      { typ: InputLabel, id: "ilosc_rat", text: "Ilosc rat"},
      { typ: InputLabel, id: "oprocentowanie", text: "Oprocentowanie"},
      { typ: InputLabel, id: "oplata_stala", text: "Stala opłata"},
      { typ: SelectlistLabel, id: "rodzaj_rat" , text: "Rodzaj rat", options: ['malejace', 'stale']},
      { typ: Input, type: "submit", value: "Wyślij"},
    ];

    return (
      <div>
        <p>Witaj na stronie liczącej raty kredytowe.</p>
        <p>Strona jest zrobiona w React+Redux.</p>
        <Formularz pola={pola} action="kalkulator" method="post"/>
        <Schedule/>
      </div>
    )
  }
}