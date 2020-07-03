import React, { Component } from 'react';


export default class SelectlistOption extends Component {
  render() {
    return (
      <option value={this.props.value}>
        {this.props.text}
      </option>
    )
  };
}