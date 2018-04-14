import React, { Component } from 'react'
import { connect } from 'react-redux'
import autobind from 'autobind-decorator'
import { actionGetPdf } from '../actions'
import PdfButton from './PdfButton'

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
          <PdfButton pdfClick={this.props.getPdf}/>
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
