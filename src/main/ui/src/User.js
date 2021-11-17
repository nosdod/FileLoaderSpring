import * as React from 'react';
import Box from '@mui/material/Box';
import TextField from '@mui/material/TextField';
import { Fab,Chip } from '@mui/material';

export default function User({checkCredentials,usertype,username,password,credsValid}) {
  const [newUsername,setNewUsername] = React.useState(username);
  const [newPassword,setNewPassword] = React.useState(password);

  function getUsernameHelperText( usertype ) {
    return "Enter the " + usertype + " account username"
  }

  function confirmOnclick() {
    console.log( 'Confirm new credentials ' + usertype + ' ' + newUsername + ' ' + newPassword);
    checkCredentials( usertype,newUsername,newPassword );
  }

  function getCheckStatus(status) {
    return status ? 'success' : 'error'
  }

  function getPasswordHelperText( usertype ) {
    return "Enter the " + usertype + " account password"
  }

  const passwordChange = (e) => {
    console.log('New Password ' + e.target.value);
    setNewPassword( e.target.value );
  }

  const usernameChange = (e) => {
    console.log('New Username ' + e.target.value);
    setNewUsername( e.target.value );
  }

  return (
    <Box
      component="form"
      sx={{
        '& .MuiTextField-root': { m: 1, width: '25ch' },
      }}
      noValidate
      autoComplete="off"
    >
        <TextField
          required
          label="User"
          helperText={getUsernameHelperText(usertype)}
          onChange={usernameChange}
          defaultValue={username}
        />
        <TextField
          label="Password"
          helperText={getPasswordHelperText(usertype)}
          type="password"
          onChange={passwordChange}
          defaultValue={password}
        />
        <Fab 
          variant="extended"
          onClick={confirmOnclick}  
          >
            Validate
        </Fab>
        <Chip label='Confirmed' color={getCheckStatus(credsValid)}/>
    </Box>
  );
}
