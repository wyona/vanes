import java.io.File;
import java.util.List;
import java.util.StringTokenizer;

import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.taskdefs.Copy;
import org.apache.tools.ant.types.FileSet;
import org.apache.tools.ant.types.Path;

/**
 *
 */
public class MCopyTask extends Copy {
    
    private Path dirs;
    
    /** 
     * @see org.apache.tools.ant.taskdefs.Copy#execute()
     */
    public void execute() throws BuildException {
                
        final StringTokenizer dirTokens = new StringTokenizer(dirs.toString(), File.pathSeparator);
            
        while (dirTokens.hasMoreTokens()) {
            final String dir = dirTokens.nextToken();
            
            for(int i=0; i<getFileSets().size(); i++)
                ((FileSet) getFileSets().get(i)).setDir(new File(dir));
            
            super.execute();
        }
    }

    /**
     * @param dirs Colon separated list of source directories
     */
    public void setDirs(Path dirs) {
        this.dirs = dirs;
    }
    
    /**
     * @return Returns the filesets
     */
    private List getFileSets() {
        return super.filesets;
    }    
}
