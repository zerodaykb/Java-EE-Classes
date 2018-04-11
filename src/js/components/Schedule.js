import React, { Component } from 'react'
import { connect } from 'react-redux'
import Input from './fieldTypes/Input'
import autobind from 'autobind-decorator/index'
import { actionSetParameters } from '../actions'

const mapStateToProps = state => {
  return {
    rows: state.form.rows,
    hasErrored: state.form.hasErrored,
    isLoading: state.form.isLoading,
  };
};

const mapDispatchToProps = dispatch => ({
  getPdf: () => dispatch(actionGetPdf()),
});

@connect(mapStateToProps, mapDispatchToProps)
export default class Schedule extends Component {

  @autobind
  handleSubmit(event) {
    event.preventDefault();
    this.props.getPdf();
  }

  render() {
    if(this.props.hasErrored) {
      return <p>Error. Przesłano złe parametry.</p>;
    }

    if(this.props.isLoading) {
      return <p>Loading...</p>
    }
    if(this.props.rows.length > 0) {
      return (
        <div className="">
          <Input type="butto" value="Generuj pdf" onSubmit={this.handleSubmit}/>
          <table className="table">
            <thead>
            <tr>
              <th>Nr raty</th>
              <th>Kwota kapitału</th>
              <th>Kwota odsetek</th>
              <th>Kwota calkowita</th>
              <th>Kwota stala</th>
            </tr>
            </thead>
            <tbody>
            {
              this.props.rows.map((row) => {
                return(
                  <tr>
                    <td>{row.nr_raty}</td>
                    <td>{row.kwota_kapitalu} zł</td>
                    <td>{row.kwota_odsetek} zł</td>
                    <td>{row.kwota_calkowita} zł</td>
                    <td>{row.oplata_stala} zł</td>
                  </tr>
                )
              })
            }
            </tbody>
          </table>
        </div>
      )
    }

    return <p>Wyślij parametery żeby poznać harmonogram.</p>;
  }
}
