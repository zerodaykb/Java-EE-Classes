import React, { Component } from 'react'

export default class AbstractField extends Component {
  render() {
    let attributes = this.props.attributes;
    let Type = attributes.typ;

    return (
      <Type
        id={attributes.id}
        name={attributes.name}
        type={attributes.type}
        value={attributes.value}
        for={attributes.for}
        text={attributes.text}
        options={attributes.options}
        update={this.props.update}
      />
    );
  };
}