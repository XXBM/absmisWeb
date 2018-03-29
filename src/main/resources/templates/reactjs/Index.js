import React from 'react';
import {Button} from 'antd';
import photo from '../../static/img/img.png';
import {
    HashRouter,
    Route,
    Link,
    Switch
} from 'react-router-dom';

class Index extends React.Component {
    render() {
        return (
            <div>
                <button>hello INDEX</button>
                <Button type="primary">hello</Button>
            </div>
        )
    }
}
export default Index