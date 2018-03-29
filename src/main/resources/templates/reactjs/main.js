/**
 * Created by apple on 17/8/1.
 */
import React from 'react';
import ReactDOM from 'react-dom';
import Login from './Login'
import Index from './Index'
import {
    HashRouter,
    Route,
    Link
} from 'react-router-dom';

ReactDOM.render(
    (
        <HashRouter>
            <div>
                <Route path="/loginPage" component={Login}/>
                <Route path="/indexPage" component={Index}/>
            </div>
        </HashRouter>
    )
    , document.getElementById('app'));
