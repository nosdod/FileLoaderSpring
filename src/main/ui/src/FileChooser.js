import * as React from 'react';
import TextField from '@mui/material/TextField';
import { Fab } from '@mui/material';
import Container from '@mui/material/Container';
import Grid from '@mui/material/Grid';

export default function FileChooser({file,fileSelected}) {    
  const inputRef = React.useRef(null);
  
  const showFileBrowser = () => {
    const { current } = inputRef;
    (current || { click: () => {}}).click()
  }

  return (
    <Container maxWidth="lg" sx={{ mt: 4, mb: 4 }}>
      <Grid container spacing={3}>
        <Grid item xs={8}>
            <TextField
              required
              readOnly
              fullWidth
              id="filename"
              label="Filename"
              value={file.name}
            />
        </Grid>
        <Grid item xs={4}>
            <Fab 
              variant="extended"
              onClick={showFileBrowser}>
                Browse
            </Fab>
            <input
              onChange={e => {
                console.log(e.target.files[0]);
                fileSelected(e.target.files[0]);
              }}
              id="select-file"
              style={{ display: 'none' }}
              type="file"
              ref={inputRef}
            />
        </Grid>
      </Grid>
    </Container>
  );
  }
