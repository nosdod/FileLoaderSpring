import * as React from 'react';
import Container from '@mui/material/Container';
import Grid from '@mui/material/Grid';
import User from './User';

export default function Credentials({checkCredentials,smCreds,kgmCreds}) {
  return (
    <Container maxWidth="lg" sx={{ mt: 4, mb: 4 }}>
        {/* SM User Details */}
        <Grid item xs={12}>
            <User usertype="SM" username={smCreds.username} password={smCreds.password} checkCredentials={checkCredentials} credsValid={smCreds.valid}/>
        </Grid>
        {/* KGM User Details */}
        <Grid item xs={12}>
            <User usertype="KGM" username={kgmCreds.username} password={kgmCreds.password} checkCredentials={checkCredentials} credsValid={kgmCreds.valid}/>
        </Grid>
    </Container>
  );
}