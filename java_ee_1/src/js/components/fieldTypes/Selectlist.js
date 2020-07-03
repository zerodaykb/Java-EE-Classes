import React, { Component } from 'react';
import SelectlistOption from './SelectlistOption'
import autobind from 'autobind-decorator'


export default class Selectlist extends Component {
  constructor(props) {
    super(props);

    this.state = {value: props.options[0]};
  }

  @autobind
  handleChange(event) {
    this.setState({value: event.target.value});
    this.props.update(this.props.id, event.target.value)
  }

  renderOptions() {
    return this.props.options.map((option) =>
      <SelectlistOption
        key={option}
        value={option}
        text={option.charAt(0).toUpperCase() + option.slice(1)}
      />
    );
  }

  componentDidMount() {
    this.props.update(this.props.id, this.state.value)
  }

  render() {
    return (
      <select
        value={this.state.value}
        id={this.props.id}
        name={this.props.name || this.props.id}
        onChange={this.handleChange}>
        {this.renderOptions()}
      </select>
    )
  };
}