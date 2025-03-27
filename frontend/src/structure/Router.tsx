import { BrowserRouter, Routes, Route } from 'react-router-dom';
import Layout from './Layout';
import Home from '../components/Home';
import Profile from '../components/Profile';
import View from '../components/View';

function Router() {
  return (
    <>
      <BrowserRouter>
          <Routes>
            <Route path="/" element={<Layout />}>
              <Route path="/home" element={<Home />}></Route>
              <Route path="/profile" element={<Profile />}></Route>
              <Route path="/view" element={<View />}></Route>
              </Route>
          </Routes>
      </BrowserRouter>
    </>
  )
}

export default Router
