import React, { Component } from 'react';
import { connect } from "react-redux";
import autobind from 'autobind-decorator';
import AbstractField from './fieldTypes/AbstractField'
import { actionSetParameters } from '../actions'

const mapDispatchToProps = dispatch => ({
  setParameters: parameters => dispatch(actionSetParameters(parameters)),
});


@connect(null, mapDispatchToProps)
export default class Form extends Component {
  constructor() {
    super();

    this.state = {
      params: {}
    };
  }

  @autobind
  updateState(field, value) {
    this.setState(prevState => ({
      params: {
        ...prevState.params,
        [field]: value
      }
    }));
  }

  @autobind
  handleSubmit(event) {
    event.preventDefault();
    this.props.setParameters(this.state.params);
  }

  getItemList() {
    let pola = this.props.pola;

    return pola.map((pole) => {
      return(
        <AbstractField
          key={pole.id}
          attributes={pole}
          update={this.updateState}
        />
      );
    });
  }

  render() {
    return (
      <form
        onSubmit={this.handleSubmit}
        action={this.props.action}
        method={this.props.method}>
        {this.getItemList()}
      </form>
    );
  }
}


Form.defaultProps = {
  method: 'get'
};
