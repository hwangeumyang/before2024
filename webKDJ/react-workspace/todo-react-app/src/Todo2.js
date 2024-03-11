import React from 'react';


class Todo2 extends React.Component {
    constructor(props) {
        super(props);
    }

    render() {
        return (
            <div className="Todo">
                <input type="checkbox"
                    id={this.props.item.id}
                    name={this.props.item.name}
                    checked={this.props.item.done}
                    />
            {this.props.item.title}Todo2
            </div>
        );
    }


}

export default Todo2;