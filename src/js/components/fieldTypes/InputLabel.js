import React, { Component } from 'react'
import Input from './Input'
import Label from './Label'

export default class InputLabel extends Component {

  render() {
    return (
      <div className="verticalLabel">
        <Label
          htmlFor={this.props.id}
          text={this.props.text}
        />
        <Input
          id={this.props.id}
          name={this.props.name}
          type={this.props.type}
          value={this.props.value}
          update={this.props.update}
        />
      </div>
    )
  };
}