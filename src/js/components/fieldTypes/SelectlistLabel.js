import React, { Component } from 'react'
import Label from './Label'
import Selectlist from './Selectlist'


export default class SelectlistLabel extends Component {

  render() {
    return (
      <div className="verticalLabel">
        <Label for={this.props.id} text={this.props.text}/>
        <Selectlist
          id={this.props.id}
          name={this.props.name || this.props.id}
          options={this.props.options}
          update={this.props.update}
        />
      </div>
    )
  };
}