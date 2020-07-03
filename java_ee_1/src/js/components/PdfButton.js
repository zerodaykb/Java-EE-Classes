import { connect } from 'react-redux'
import React, { Component } from 'react'
import autobind from 'autobind-decorator'

const mapStateToProps = (state) => {
  return {
    hasErrored: state.pdf.hasErrored,
    isLoading: state.pdf.isLoading,
  };
};

@connect(mapStateToProps)
export default class Schedule extends Component {

  @autobind
  renderInfo() {
    if (this.props.hasErrored) {
      return <p>Error. Przesłano złe parametry.</p>;
    }

    if (this.props.isLoading) {
      return <p>Loading...</p>
    }
  }

  render () {
    const { pdfClick } = this.props;

    return (
      <div>
        <button
          type="button"
          className="generatorPdf"
          onClick={pdfClick}>
          Wygeneruj pdf!
        </button>
        <p>
          {this.renderInfo}
        </p>
      </div>

    )


  }
}
