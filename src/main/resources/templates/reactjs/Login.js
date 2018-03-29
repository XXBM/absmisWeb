import React from 'react';
import {Button, Form, Icon, Input} from 'antd';
import {
    HashRouter,
    Route,
    Link,
    Switch
} from 'react-router-dom';
const FormItem = Form.Item;
class Login extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            url: []
        };
        this.handleSubmit=this.handleSubmit.bind(this);
    }
    componentDidMount() {
        // var ip=this.props.match.url+" ";
        // this.setState({url:ip.substring(0,10)+"indexPage"});
        // console.log(this.state.url);
    }
    handleSubmit(e) {
        e.preventDefault();
        //可以验证表单填写内容
        this.props.form.validateFields((err, values) => {
            if (!err) {
                console.log('Received values of form: ', values);
                fetch("/absmis/login1", {
                    method: "POST",
                    mode:'cors',
                    headers: {
                        'Content-Type': 'application/x-www-form-urlencoded'
                    },
                    body:`username=${values.username}&password=${values.password}`

                }).then(function(res) {
                    if (res.status == 200) {
                        alert("Perfect! log in.");
                    } else if (res.status == 401) {
                        alert("sorry.");
                    }
                })
            }
        });
        let data = this.props.form.getFieldsValue();
        console.log(e+"*****");
        console.log(e[0]+"*---*");
        console.log(' form: ', data);
        //此处提交表单,但是这里还需要刷新页面下面的表格，这个还不知道怎么刷新
    }
    render() {
        const { getFieldDecorator } = this.props.form;
        return (
            <center>
                <Link to={`${this.props.match.url}/indexPage`}>toindex</Link>
                <Form  id="form" onSubmit={this.handleSubmit} className="login-form">
                    <FormItem>
                        {getFieldDecorator('username', {
                            rules: [{ required: true, message: 'Please input your username!' }],
                        })(
                            <Input style={{  width: '30%' }} prefix={<Icon type="user" style={{ fontSize: 13 }} />} placeholder="Username" />
                        )}
                    </FormItem>
                    <FormItem>
                        {getFieldDecorator('password', {
                            rules: [{ required: true, message: 'Please input your Password!' }],
                        })(
                            <Input style={{  width: '30%'}} prefix={<Icon type="lock" style={{ fontSize: 13 }} />} type="password" placeholder="Password" />
                        )}
                    </FormItem>
                    <FormItem>
                        <Button type="primary" htmlType="submit" className="login-form-button">
                            登陆
                        </Button>
                    </FormItem>
                </Form>
            </center>
        )
    }
}
Login = Form.create({})(Login);
export default Login