import React, { Component } from 'react';
import autobind from 'autobind-decorator';


export default class Input extends Component {
  constructor() {
    super();

    this.state = {value: ''};
  }

  @autobind
  handleChange(event) {
    this.setState({value: event.target.value});
    this.props.update(this.props.id, event.target.value)
  }

  render() {
    return (
      <input
        id={this.props.id}
        name={this.props.name || this.props.id}
        type={this.props.type}
        value={this.props.value || this.state.value}
        onChange={this.handleChange}
        onSubmit={this.props.handleSubmit}
      />
    )
  };
}

Input.defaultProps = {
  type: 'text'
};
